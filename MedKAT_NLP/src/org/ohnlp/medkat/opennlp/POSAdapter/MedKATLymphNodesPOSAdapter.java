
package org.ohnlp.medkat.opennlp.POSAdapter;


import org.ohnlp.medkat.POSTaggerAdaptorBase.LymphNodesPOSAdapterBase;

public class MedKATLymphNodesPOSAdapter extends LymphNodesPOSAdapterBase
{
    public MedKATLymphNodesPOSAdapter ()
    {
        super("uima.tt.TokenAnnotation", "pennTag");
    }
}
