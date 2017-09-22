
import {Component, Input, OnInit} from '@angular/core';
import {PokerCardDTO} from '../../../../model/poker.card.dto';

@Component({
  selector: 'app-poker-card',
  templateUrl: './poker-card.component.html',
  styleUrls: ['./poker-card.component.scss']
})
export class PokerCardComponent implements OnInit {

  @Input()
  public card: PokerCardDTO;
  @Input()
  public flipped: Boolean = false;

  constructor() { }

  ngOnInit() {
  }

}
