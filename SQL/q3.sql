select book.title from book
where book.id not in(select book_id from checkout_item where book_id is not null)
union
select movie.title from movie 
where movie.id not in(select movie_id from checkout_item where movie_id is not null);

