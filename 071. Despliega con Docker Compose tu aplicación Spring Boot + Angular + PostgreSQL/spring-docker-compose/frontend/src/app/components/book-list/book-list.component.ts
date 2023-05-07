import {Component, OnInit} from '@angular/core';
import {BookService} from 'src/app/services/book.service';
import {Book} from "../../models/book";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books?: Book[];

  constructor(private bookService: BookService) {
  }

  ngOnInit(): void {
    this.findAll();
  }

  private findAll() {
    this.bookService.getAll().subscribe({
      next: value => {
        this.books = value;
        console.log(value)
      },
      error: error => {console.error(error)}
    })
  }
}
