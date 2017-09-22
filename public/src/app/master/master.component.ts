import { Component, OnInit, OnDestroy } from '@angular/core';
import { NewGame} from '../services/newGame.service';
//import { ActivatedRoute, Params} from '@angular/router';


export class player{
  name:string;
  selectednum:string;
}

  
  export class game{
    name:string;
    players: player[];
  }
  

@Component({
  moduleId: module.id,
  templateUrl: 'master.component.html',
  providers:[NewGame]
})

export class MasterComponent {
    games:game;
    name:string;
    id:string;
    players:player[];
    constructor(private newGame:NewGame){
    }
  
    

  create(event){
    event.preventDefault();
    var newGame1= {
      name:this.name,
    }
      var x = "You Have Created A Game. Goto Active Games to Start The Game";
     this.newGame.create(newGame1).subscribe( game=> {this.games=game;});
    
    }



}