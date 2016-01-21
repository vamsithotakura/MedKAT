
package org.ohnlp.medkat.opennlp.POSAdapter;


import org.ohnlp.medkat.POSTaggerAdaptorBase.LeftRightPOSAdapterBase;

public class MedKATLeftRightPOSAdapter extends LeftRightPOSAdapterBase
{
    public MedKATLeftRightPOSAdapter ()
    {
        super("uima.tt.TokenAnnotation", "pennTag");
    }
}
