import { Component } from '@angular/core';
import { NewGame} from './services/newGame.service'

@Component({
  moduleId: module.id,
  selector: 'app',
  templateUrl: 'app.component.html',
  providers:[NewGame]
})

export class AppComponent  {

  constructor(){
  }


}
