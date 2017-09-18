import { Component } from '@angular/core';
import {PlayService} from '../../services/play.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private _playService: PlayService) { }

  private _join() {
    this._playService.isSessionAvailable().then(result => {
      alert(result);
    });
  }

}
