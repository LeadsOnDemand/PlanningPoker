import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class NewGame{
    constructor(private http:Http){
        
    }
    
    getGame()
    {
        return this.http.get('http://localhost:80/activegames' ).map(res => res.json());
    }

    getPlayers(x)
    {
        return this.http.get('http://localhost:80/mygame/'+ x ).map(res => res.json());
    }

    create(newGame1)
    {
        var headers= new Headers();
        console.log(newGame1);
        headers.append('Content-Type','application/json');
        return this.http.post('http://localhost:80/master', JSON.stringify(newGame1) , {headers:headers}).map(res => res.json());
    }

    deleteGame(id){
        return this.http.delete('http://localhost:80/activegames/' + id ).map( res => res.json());
    }

    deletePlayer(id){
        return this.http.delete('http://localhost:80/mygame/' + id ).map( res => res.json());
    }
    join(newPlayer)
    {
        var headers= new Headers();
        console.log(newPlayer);
        return this.http.put('http://localhost:80/join/'+  newPlayer.joinId + '/' + newPlayer.yname + '/' + newPlayer.id , JSON.stringify(newPlayer) ,{headers:headers}).map(res => res.json());
    }


    a(i)
    {
        var headers= new Headers();
        return this.http.put('http://localhost:80/join/'+  i.joinId + '/' + 1 + '/done', JSON.stringify(i) ,{headers:headers}).map(res => res.json());
    }

    b(i)
    {
        var headers= new Headers();
        return this.http.put('http://localhost:80/join/'+  i.joinId + '/' + 2 + '/done' , JSON.stringify(i) ,{headers:headers}).map(res => res.json());
    }
}