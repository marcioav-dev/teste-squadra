import { Injectable } from '@angular/core';
import { Sistema } from './sistemas/sistema';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { PaginaSistemas } from './pagina.sistemas';

@Injectable({
  providedIn: 'root'
})
export class SistemasService {

  constructor(private http: HttpClient) { }

  getSistema(): Sistema{
    let sistema = new Sistema();
    sistema.descricao = 'a';
    sistema.sigla = 'b';
    sistema.email_de_atendimento_sistema = 'c';
    sistema.url_sistema = 'd';
    return sistema;
  }

  salvarSistema(sistema: Sistema) : Observable<Sistema>{
    return this.http.post<Sistema>('http://localhost:8080/api/sistemas', sistema);
  }

  atualizarSistema(sistema: Sistema) : Observable<any>{
    return this.http.put<Sistema>(`http://localhost:8080/api/sistemas/${sistema.id}`, sistema);
  }

  getSistemas(): Observable<Sistema[]>{
    return this.http.get<Sistema[]>('http://localhost:8080/api/sistemas');
  }

  getSistemaById(id: number) : Observable<Sistema> {
    return this.http.get<Sistema>(`http://localhost:8080/api/sistemas/${id}`);
  }

  findSistemas(descricao: string, sigla: string, email_de_atendimento_sistema: string, page, size): Observable<PaginaSistemas>{
    if(descricao === ''){
      descricao = null;
    }
    if(sigla === ''){
      sigla = null;
    }
    if(email_de_atendimento_sistema === ''){
      email_de_atendimento_sistema = null;
    }
    let params = new HttpParams()
    .set('descricao', descricao)
    .set('sigla', sigla)
    .set('email', email_de_atendimento_sistema)
    .set('page', page)
    .set('size', size);
    return this.http.get<PaginaSistemas>('http://localhost:8080/api/sistemas/pesquisar',{params: params});
  }
}
