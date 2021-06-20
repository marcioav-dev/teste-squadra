import { Component, OnInit } from '@angular/core';
import { SistemasService } from 'src/app/sistemas.service';
import { Sistema } from '../sistema';
import { Router, ActivatedRoute } from '@angular/router'

@Component({
  selector: 'app-form-sistema',
  templateUrl: './form-sistema.component.html',
  styleUrls: ['./form-sistema.component.css']
})
export class FormSistemaComponent implements OnInit {

  sistema: Sistema;
  isSuccess: boolean = false;
  error: string;
  errors: String[];
  id: number;
  nova_justificativa_alteracao: string;

  constructor(private service: SistemasService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
    this.sistema = new Sistema();
   }

  ngOnInit(): void {
    let param = this.activatedRoute.params
    param.subscribe( urlParams => {
      this.id = urlParams['id'];
      this.service.getSistemaById(this.id)
      .subscribe(response => this.sistema = response,
        errorResponse => this.sistema = new Sistema());
    })
  }

  testarDados(){
    console.log(this.sistema);
  }

  voltar(){
    this.router.navigate(['pesquisar']);
  }

  enviarFormulario(){
    if(this.id){
      if(this.nova_justificativa_alteracao){
        this.sistema.justificativa_ultima_alteracao = this.nova_justificativa_alteracao;
      }
      this.service.atualizarSistema(this.sistema)
      .subscribe(response => {
        this.error = null;
        this.errors = [];
        this.isSuccess = true;
        this.sistema = new Sistema();
      }, errorResponse => {
        this.error = 'Dados obrigatórios não informados.'
      });
    }else{
      this.service.salvarSistema(this.sistema)
      .subscribe( response =>{
        this.error = null;
        this.errors = [];
        this.isSuccess = true;
        this.sistema = new Sistema();
      }, errorResponse => {
        console.log(errorResponse.error.errors[0]);
        this.error = errorResponse.error.errors[0];
        //this.errors = errorResponse.error.errors;
      });
    }
  }
}
