import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule }    from '@angular/http';  
import { AppComponent }  from './app.component';
import { AppComponentA }  from './activegames/app.componentA';
import { MasterComponent }  from './master/master.component';
import { MygameComponent }  from './mygame/mygame.component';
import { JoinComponent }  from './join/join.component';
import { FormsModule }   from '@angular/forms';
import { routes } from './app.router';


@NgModule({
  imports:      [ BrowserModule , HttpModule, FormsModule, routes],
  declarations: [ AppComponent, AppComponentA, MasterComponent, MygameComponent,JoinComponent],
  bootstrap:    [ AppComponent]
})
export class AppModule { }


