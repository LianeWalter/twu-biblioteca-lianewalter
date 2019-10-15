Select member.name from member 
where member.id not in(select member_id from checkout_item);