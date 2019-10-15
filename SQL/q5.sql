select member.name from checkout_item, member 
where checkout_item.member_id = member.id
group by  member.name
having count(checkout_item.member_id) > 2;

