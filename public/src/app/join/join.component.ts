import { Component } from '@angular/core';
import { NewGame} from '../services/newGame.service'



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
  templateUrl: 'join.component.html',
  providers:[NewGame]
})

export class JoinComponent  {
  constructor(private newGame:NewGame){
  }

  joinId:string;
  yname:string;
  games:game;

  a(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:0
    }
   return this.newGame.a(newPlayer).subscribe( game => {this.games=game;});
  }

  b(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:1
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  c(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:2
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  d(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:3
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  e(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:5
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  f(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:8
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  g(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:13
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  h(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:21
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  i(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:34
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  j(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:55
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

  k(event)
  {
    event.preventDefault();
    var newPlayer= {
      joinId:this.joinId,
      yname:this.yname,
      id:89
    }
   return this.newGame.join(newPlayer).subscribe( game => {this.games=game;});
  }

}
