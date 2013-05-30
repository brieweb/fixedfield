package test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import fixedfield.FixedFieldLexer;
import fixedfield.FixedFieldParser;
import fixedfield.ast.ASTBuilder;
import fixedfield.ast.DSetNode;
import fixedfield.ast.FixNode;
import fixedfield.ast.RecordNode;





public class E1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Scanner inputf;
		
		if (args.length < 2) {
			System.err.println("usage: java test.E1 format.txt sample.dat");
			System.exit(0);
		}
		
        String inputFile = null;
        if ( args.length>0 ) 
        	inputFile = args[0];
        
        InputStream is = System.in;
        if ( inputFile!=null ) 
        	is = new FileInputStream(inputFile);
        
        ANTLRInputStream input = new ANTLRInputStream(is);
        FixedFieldLexer lexer = new FixedFieldLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FixedFieldParser parser = new FixedFieldParser(tokens);
        ParseTree tree = parser.dataset();// parse

        ASTBuilder eval = new ASTBuilder();
        DSetNode dsn = (DSetNode) eval.visit(tree);
        System.out.println(dsn);
        
        try {
        	List<RecordNode> rns = dsn.getRecords();
        	inputf = new Scanner( new File(args[1]));
        	
        	for (Iterator iterator = rns.iterator(); iterator.hasNext();) {
				RecordNode recordNode = (RecordNode) iterator.next();
				System.out.println(recordNode.getId());
				if (inputf.hasNext()) {
					String nl = inputf.nextLine();
	        		//System.out.println(nl);
	        		List<FixNode> fns = recordNode.getFields();
	        		for (Iterator iterator2 = fns.iterator(); iterator2
							.hasNext();) {
						FixNode fixNode = (FixNode) iterator2.next();
						String FieldId = fixNode.getId();
						int beginIndex = fixNode.getStart();
						int endIndex = fixNode.getStart() + fixNode.getCount();
						String content = nl.substring(beginIndex, endIndex);
						System.out.println("  " + FieldId + ": " + content);
						
					}
				}
			}
        	
        	
        } catch ( FileNotFoundException fileNotFoundException )
        {   
        	System.err.println( "Error opening file." );
        	System.exit( 1 );
        } // end catch
        
        
	}

}
