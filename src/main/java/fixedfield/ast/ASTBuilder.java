package fixedfield.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fixedfield.FixedFieldBaseVisitor;
import fixedfield.FixedFieldParser.DatasetContext;
import fixedfield.FixedFieldParser.FieldContext;
import fixedfield.FixedFieldParser.PicAContext;
import fixedfield.FixedFieldParser.PicACountContext;
import fixedfield.FixedFieldParser.PicIntContext;
import fixedfield.FixedFieldParser.PicIntCountContext;
import fixedfield.FixedFieldParser.PicXContext;
import fixedfield.FixedFieldParser.PicXCountContext;
import fixedfield.FixedFieldParser.RecordContext;

public class ASTBuilder extends FixedFieldBaseVisitor<ASTNode>{
	
	private int colIdx = 0;
	private int rowIdx = 0;

	@Override
	public ASTNode visitField(FieldContext ctx) {
		FixNode a = (FixNode) visit(ctx.picture());
		String id = ctx.ID().getText();
		a.setStart(colIdx);
		a.setId(id);
		
		colIdx = colIdx + a.getCount();
		
		return a;
	}

	@Override
	public ASTNode visitRecord(RecordContext ctx) {
		List<FixNode> b = new ArrayList<FixNode>();
		
		List<FieldContext> a = ctx.field();
		
		for (Iterator iterator = a.iterator(); iterator.hasNext();) {
			FieldContext fieldContext = (FieldContext) iterator.next();
			FixNode c = (FixNode) visit (fieldContext);
			b.add(c);
		} 
		
		RecordNode rn = new RecordNode();
		rn.setFields(b);
		String id = ctx.ID().getText();
		rn.setId(id);
		
		// Reset back to zero
		colIdx = 0;
		
		// TODO Auto-generated method stub
		return rn;
	}

	@Override
	public ASTNode visitPicA(PicAContext ctx) {
		ANode a = new ANode();
		a.setCount(ctx.PICA().getText().length());
		return a;
	}

	@Override
	public ASTNode visitPicInt(PicIntContext ctx) {
		NineNode a = new NineNode();
		String fstring = ctx.INT().getText();
		
		// does fstring contain all "999"s ?
		a.setCount(fstring.length());
		return a;
	}

	@Override
	public ASTNode visitPicXCount(PicXCountContext ctx) {
		int count = 0;
		if (ctx.INT() != null) {
			//System.out.println("We have a two digit int");
			count = Integer.parseInt(ctx.INT().getText());
		} else {
			//System.out.println("We have a one digit int");
			count = Integer.parseInt(ctx.INTONE().getText());
		}
		XNode a = new XNode();
		a.setCount(count);
		return a;
	}

	@Override
	public ASTNode visitPicACount(PicACountContext ctx) {
		int count = 0;
		if (ctx.INT() != null) {
			//System.out.println("We have a two digit int");
			count = Integer.parseInt(ctx.INT().getText());
		} else {
			//System.out.println("We have a one digit int");
			count = Integer.parseInt(ctx.INTONE().getText());
		}
		ANode a = new ANode();
		a.setCount(count);
		return a;
	}

	@Override
	public ASTNode visitDataset(DatasetContext ctx) {
		List<RecordContext> a = ctx.record();
		List<RecordNode> b = new ArrayList<RecordNode>();
		
		for (RecordContext recordContext : a) {
			RecordNode c = (RecordNode)visit(recordContext);
			c.setRow(rowIdx);
			b.add(c);
			rowIdx++;
			
		}
		
		DSetNode d = new DSetNode();
		d.setRecords(b);
		return d;
	}

	@Override
	public ASTNode visitPicX(PicXContext ctx) {
		// TODO Auto-generated method stub
		//System.out.println("Found an Xs " + ctx.getText() + " is  " + ctx.getText().length() + " characters long");
		//System.out.println("ctx is " + ctx.PICX());
		
		XNode a = new XNode();
		a.setCount(ctx.PICX().getText().length());
		return a;
		
	}

	@Override
	public ASTNode visitPicIntCount(PicIntCountContext ctx) {
		String nineText = ctx.INTONE(0).getText();
		if (! nineText.matches("9")) 
			System.out.println("No 9! Not according to specification!");
			
		int count = 0;
		if (ctx.INT() != null) {
			//System.out.println("We have a two digit int");
			count = Integer.parseInt(ctx.INT().getText());
		} else {
			//System.out.println("We have a one digit int");
			count = Integer.parseInt(ctx.INTONE(1).getText());
		}
		NineNode a = new NineNode();
		a.setCount(count);
		return a;
	}

}
