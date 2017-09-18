import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {AppComponent} from "./components/app/app.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {PlayService} from "./services/play.service";
import {MdButtonModule, MdIconModule, MdIconRegistry, MdToolbarModule} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MdToolbarModule,
    MdIconModule,
    MdButtonModule
  ],
  providers: [
    MdIconRegistry,
    PlayService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
