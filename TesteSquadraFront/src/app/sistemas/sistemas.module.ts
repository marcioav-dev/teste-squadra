import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SistemasRoutingModule } from './sistemas-routing.module';
import { FormSistemaComponent } from './form-sistema/form-sistema.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ListarSistemasComponent } from './listar-sistemas/listar-sistemas.component';
import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
  declarations: [
    FormSistemaComponent,
    ListarSistemasComponent
  ],
  imports: [
    CommonModule,
    SistemasRoutingModule,
    RouterModule,
    FormsModule,
    MatPaginatorModule
  ],
  exports: [
    FormSistemaComponent,
    ListarSistemasComponent
  ]
})
export class SistemasModule { }
