import {Component, OnInit} from '@angular/core';
import {ConfigurationService} from '../../services/config.service';
import {PokerCardDTO} from '../../../../model/poker.card.dto';

@Component({
  selector: 'app-play-board',
  templateUrl: './play-board.component.html',
  styleUrls: ['./play-board.component.scss']
})
export class PlayBoardComponent implements OnInit {

  private _cardDeck: PokerCardDTO[];

  constructor(private _configurationService: ConfigurationService) { }

  ngOnInit() {
    this._configurationService.getConfiguration().then(config => {
      this._cardDeck = config.cardDeck;
    });
  }

}
