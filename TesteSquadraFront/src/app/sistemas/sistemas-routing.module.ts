import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormSistemaComponent } from './form-sistema/form-sistema.component';
import { ListarSistemasComponent } from './listar-sistemas/listar-sistemas.component';


const routes: Routes = [
  {path: 'cadastrar', component: FormSistemaComponent},
  {path: 'cadastrar/:id', component: FormSistemaComponent},
  {path: 'pesquisar', component: ListarSistemasComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SistemasRoutingModule { }
