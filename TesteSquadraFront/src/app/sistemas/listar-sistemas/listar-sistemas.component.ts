import { Component, OnInit } from '@angular/core';
import { SistemasService } from 'src/app/sistemas.service';
import { Sistema } from '../sistema';
import { Router } from '@angular/router'
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-listar-sistemas',
  templateUrl: './listar-sistemas.component.html',
  styleUrls: ['./listar-sistemas.component.css']
})
export class ListarSistemasComponent implements OnInit {
  
  sistemas: Sistema[] = null;
  descricao: string = null;
  sigla: string = null;
  email_de_atendimento_sistema: string = null;
  error: string;
  totalElements = 0;
  page = 0;
  pageIndex = 0;
  size = 2;
  pageSizeOptions : number[] = [10,30,50];

  constructor(private service: SistemasService,
              private router: Router) { 

  }

  ngOnInit(): void {
  }

  limparCampos(){
    this.descricao = null;
    this.sigla = null;
    this.email_de_atendimento_sistema = null;
    this.sistemas = null;
  }

  pesquisaTeste(){
    this.service.getSistemas()
    .subscribe( response => this.sistemas = response);
  }

  novoSistema(){
    this.router.navigate(['/cadastrar']);
  }

  pesquisarSistema(){
    this.error = null;
    console.log(this.email_de_atendimento_sistema)
    this.service.findSistemas(this.descricao, this.sigla, this.email_de_atendimento_sistema, this.page, this.size)
    .subscribe(response =>{
      this.sistemas = response.content;
      console.log('Sistemas = ' + this.sistemas.length.toString());
      if(this.sistemas.length === 0){
        this.error = "Nenhum Sistema foi encontrado. Favor revisar os critérios da sua pesquisa!";
        return;
      }
      this.totalElements = response.totalElements;
      this.page = response.number;
      this.pageIndex = response.number;
      console.log(this.totalElements);
      console.log(this.page);
    }, errorResponse => {
      this.error = "Nenhum Sistema foi encontrado. Favor revisar os critérios da sua pesquisa!";
    });
  }

  paginar(event: PageEvent){
    this.page = event.pageIndex;
    this.pesquisarSistema();
  }
}
