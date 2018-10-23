package kumaxiong.com.preferencehelper.visitor;
public abstract class BaseVisitor {


  protected String formatKey(String key) {
    return key.toLowerCase();
  }
}
