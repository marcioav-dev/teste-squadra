import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SistemasModule } from './sistemas/sistemas.module';
import { SistemasService } from './sistemas.service';
import { HttpClientModule } from '@angular/common/http'
 
@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SistemasModule,
    HttpClientModule
  ],
  providers: [
    SistemasService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
 