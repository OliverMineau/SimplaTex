package View;

import javax.swing.text.*;
import javax.swing.text.html.HTML;

public class PreserveSpanDocumentFilter extends DocumentFilter {
    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        // Check if the content being removed is within a <span> tag
        Document doc = fb.getDocument();
        Element root = doc.getDefaultRootElement();

        if(root.getElementCount() >=2 ){
            Element elem = root.getElement(1).getElement(root.getElement(1).getElementIndex(offset));
            AttributeSet attrs = elem.getAttributes();
            Object name = attrs.getAttribute(StyleConstants.NameAttribute);

            //root.getElement(1).getElement(root.getElement(1).getElementIndex(offset)).getName()
            //fb.getDocument().getText(offset-100,length+100)
            if (name instanceof HTML.Tag){
                if( name instanceof HTML.UnknownTag){
                    System.out.println("CustomTag : " + name);
                    return;
                }
            }

        }


        System.out.println("Removing");
        // Otherwise, proceed with the default behavior
        super.remove(fb, offset, length);
    }
}
