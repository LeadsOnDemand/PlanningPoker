import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './components/app/app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {PlayService} from './services/play.service';
import {
  MdButtonModule, MdCardModule, MdIconModule, MdIconRegistry, MdInputModule,
  MdToolbarModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SigninComponent} from './components/signin/signin.component';
import {PlayHandler} from '../../handler/play.handler';
import {AvatarComponent} from './components/avatar/avatar.component';
import {PokerCardComponent} from './components/poker-card/poker-card.component';
import {PlayBoardComponent} from './components/play-board/play-board.component';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app.routing.module';
import {AuthenticationProvider} from './providers/authentication.provider';
import {ConfigurationService} from "./services/config.service";

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    AvatarComponent,
    PokerCardComponent,
    PlayBoardComponent
  ],
  imports: [
    RouterModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MdCardModule,
    MdToolbarModule,
    MdIconModule,
    MdButtonModule,
    MdInputModule
  ],
  providers: [
    AuthenticationProvider,
    ConfigurationService,
    MdIconRegistry,
    PlayService,
    PlayHandler
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
