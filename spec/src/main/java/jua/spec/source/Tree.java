package jua.spec.source;

public interface Tree {
    
    interface Tag {
        
    }
    
    interface Visitor {
        
    }
    
    Tag getTag();
    
    void accept(Visitor visitor);
    
    interface Statement extends Tree {
        
        int pos();
    }
    
    interface Expression extends Statement {
        
    }
}
