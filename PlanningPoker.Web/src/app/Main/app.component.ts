//import { Component, OnInit } from '@angular/core';
////import { signalR } from 'scripts/signalr-client.signalR';

//declare var signalR: any;

//@Component({
//    selector: 'app-root',
//    templateUrl: './app.component.html',
//    styleUrls: ['./app.component.css']
//})
//export class AppComponent implements OnInit {
//    title = 'Welcome to Planning Poker!';



//    ngOnInit() {
//        console.log('init');
//        let connection = new signalR.HubConnection('http://10.0.0.173/PlanningPoker.Api/planning');
//        connection.on('Join', data => {
//            console.log(data);
//        });

//        connection.start().then(() => connection.invoke('Join', 'Hello'));
//    }

//}
