package j.a.b.s.ics372.gp1.grocerystore.business.interfaces;

import j.a.b.s.ics372.gp1.grocerystore.business.entities.Member;
import j.a.b.s.ics372.gp1.grocerystore.business.entities.Product;
import j.a.b.s.ics372.gp1.grocerystore.business.facade.Result;

public interface IteratorVisitor {
	public Result visit (Member member);
	public Result visit (Product product);
	public Result next(Member member);
}
