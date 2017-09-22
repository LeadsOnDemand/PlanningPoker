import { Component, OnInit, Input} from '@angular/core';
import { NewGame} from '../services/newGame.service'


export class player{
  name:string;
  selectednum:string;
  _id:string;
}

  
  export class game{
    _id:string;
    name:string;
    players: player[];
  }
  

@Component({
  moduleId: module.id,
  templateUrl: 'mygame.component.html',
  providers:[NewGame]
})

export class MygameComponent {
  constructor(private newGame:NewGame){
  }
  games:game[];
  name:string;
 players:player[];
 playerId:string;

 _id:string;

getPlayers(event)
  {
    event.preventDefault();
    var ead= {
      playerId:this.playerId
    }
    console.log(ead.playerId);
    return this.newGame.getPlayers(ead.playerId).subscribe( player=> {this.players=player;});
  }

  deletePlayer(id){
    var players = this.players;
    
        return this.newGame.deletePlayer(id).subscribe( data =>
        {
          for(var i= 0;i<players.length;i++)
            {
              if(players[i]._id == id)
                {
                  players.splice(i,1);
                }
            }
        });
    }

}
