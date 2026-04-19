package org.example.libImposto.model;

/**
 * Classe que representa o resultado do cálculo de impostos.
 * 
 * @author libImposto
 * @version 1.0
 * @since 1.0
 */
public class ResultadoImpostos {
    
    private float irpj;
    private float cofins;
    private float pis;
    private float csll;
    private float ipi;
    private float icms;
    private float ipva;
    private float itcmd;
    private float iss;
    
    public ResultadoImpostos() {}
    
    // Getters e Setters
    public float getIrpj() { return irpj; }
    public void setIrpj(float irpj) { this.irpj = irpj; }
    
    public float getCofins() { return cofins; }
    public void setCofins(float cofins) { this.cofins = cofins; }
    
    public float getPis() { return pis; }
    public void setPis(float pis) { this.pis = pis; }
    
    public float getCsll() { return csll; }
    public void setCsll(float csll) { this.csll = csll; }
    
    public float getIpi() { return ipi; }
    public void setIpi(float ipi) { this.ipi = ipi; }
    
    public float getIcms() { return icms; }
    public void setIcms(float icms) { this.icms = icms; }
    
    public float getIpva() { return ipva; }
    public void setIpva(float ipva) { this.ipva = ipva; }
    
    public float getItcmd() { return itcmd; }
    public void setItcmd(float itcmd) { this.itcmd = itcmd; }
    
    public float getIss() { return iss; }
    public void setIss(float iss) { this.iss = iss; }
    
    public float getTotal() {
        return irpj + cofins + pis + csll + ipi + icms + ipva + itcmd + iss;
    }
    
    @Override
    public String toString() {
        return String.format(
            "IRPJ: R$ %.2f, COFINS: R$ %.2f, PIS: R$ %.2f, CSLL: R$ %.2f, IPI: R$ %.2f, ICMS: R$ %.2f, IPVA: R$ %.2f, ITCMD: R$ %.2f, ISS: R$ %.2f, TOTAL: R$ %.2f",
            irpj, cofins, pis, csll, ipi, icms, ipva, itcmd, iss, getTotal()
        );
    }
}
