public class XmlToJsonAdapter {

    private XmlService xmlService;

    public XmlToJsonAdapter(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    // JSON → XML
    public void sendJson(String json) {
        String xml = "<data>" + json + "</data>";
        xmlService.receiveXml(xml);
    }

    // XML → JSON (NHẬP TỪ CLIENT)
    public String convertXmlToJson(String xml) {
        return "{ \"xmlData\": \"" + xml.replace("\"", "\\\"") + "\" }";
    }

    String getJsonFromXml() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
