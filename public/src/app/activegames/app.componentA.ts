import { Component } from '@angular/core';
import { NewGame} from '../services/newGame.service'

export class player{
  name:string;
  selectednum:string;
}

  
  export class game{
    _id:string;
    name:string;
    players: player[];
  }
  
@Component({
  moduleId: module.id,
  templateUrl: 'app.componentA.html',
  providers:[NewGame]
})

export class AppComponentA  {

  games:game[];
  name:string;
  _id:string;
  players:string[];

  
  constructor(private newGame:NewGame){
  }

  getGame()
  {
    return this.newGame.getGame().subscribe( game => {this.games=game;});
  }
 

  deleteGame(id){
    var games = this.games;
    
        return this.newGame.deleteGame(id).subscribe( data =>
        {
          for(var i= 0;i<games.length;i++)
            {
              if(games[i]._id == id)
                {
                  games.splice(i,1);
                }
            }
        });
    }

}