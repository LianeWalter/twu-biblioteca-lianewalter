/*
Add the book 'The Pragmatic Programmer', and add yourself as a member. 
Check out 'The Pragmatic Programmer'. 
Use your query from question 1 to verify that you have checked it out.
 Also, provide the SQL used to update the database.
 */

insert into book(id, title) values (11,'The Pragmatic Programmer');
insert into member(id, name) values (43, 'Liane Walter');
insert into checkout_item(member_id, book_id, movie_id) values (43,11,null);

select member.name from checkout_item, book, member 
where checkout_item.member_id = member.id 
and checkout_item.book_id = book.id and book.title like 'The Pragmatic Programmer';