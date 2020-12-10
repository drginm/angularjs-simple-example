import { Component, Input, OnInit } from '@angular/core';
import { MapTo } from '@adobe/cq-angular-editable-components';

const InterestingContentEditConfig = {
  emptyLabel: 'Interesting Content',
  isEmpty: cqModel =>
    !cqModel || !cqModel.interestingPages
};

@Component({
  selector: 'app-interesting-content',
  templateUrl: './interesting-content.component.html',
  styleUrls: ['./interesting-content.component.css']
})
export class InterestingContentComponent implements OnInit {
  @Input() interestingPages: object[];

  constructor() { }

  ngOnInit() {
  }

}

MapTo('angularjs-simple-example/components/interesting-content')(
  InterestingContentComponent,
  InterestingContentEditConfig
);
