import {Component, OnInit} from '@angular/core';
import {PlayHandler} from '../../../../handler/play.handler';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  private _initialized: Boolean = false;
  private _isSessionAvailable: Boolean = false;

  constructor(private _playHandler: PlayHandler) { }

  ngOnInit(): void {
    this._playHandler.isSessionAvailable().then(result => {
      this._initialized = true;
      this._isSessionAvailable = result;
    });
  }

}
