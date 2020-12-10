package ua.sampsa.addressbook.model;

public class GroupData {
  private final String name;
  private final String header;
  private final String footer;

  public GroupData(String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public static class NewContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String companyName;
    private final String address;
    private final String mobilePhone;
    private final String email;
    private final String dayOfBirth;
    private final String monthOfBirth;
    private final String yearOfBirth;
  
    public NewContactData(String firstName, String middleName, String lastName, String nickName, String companyName, String address, String mobilePhone, String email, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.nickName = nickName;
      this.companyName = companyName;
      this.address = address;
      this.mobilePhone = mobilePhone;
      this.email = email;
      this.dayOfBirth = dayOfBirth;
      this.monthOfBirth = monthOfBirth;
      this.yearOfBirth = yearOfBirth;
    }
  
    public String getFirstName() {
      return firstName;
    }
  
    public String getMiddleName() {
      return middleName;
    }
  
    public String getLastName() {
      return lastName;
    }
  
    public String getNickName() {
      return nickName;
    }
  
    public String getCompanyName() {
      return companyName;
    }
  
    public String getAddress() {
      return address;
    }
  
    public String getMobilePhone() {
      return mobilePhone;
    }
  
    public String getEmail() {
      return email;
    }
  
    public String getDayOfBirth() {
      return dayOfBirth;
    }
  
    public String getMonthOfBirth() {
      return monthOfBirth;
    }
  
    public String getYearOfBirth() {
      return yearOfBirth;
    }
  }
}
