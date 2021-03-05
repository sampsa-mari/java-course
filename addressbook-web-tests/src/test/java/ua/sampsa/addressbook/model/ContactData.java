package ua.sampsa.addressbook.model;

/**
 * Created by Maryna Tkachuk on 15.12.20.
 */
public class ContactData {
    private int id = 0;
    private String lastName;
    private String middleName;
    private String firstName;
    private String nickName;
    private String companyName;
    private String address;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String email;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public ContactData withCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withDayOfBirth(String dayOfBirth) {
    this.dayOfBirth = dayOfBirth;
    return this;
  }

  public ContactData withMonthOfBirth(String monthOfBirth) {
    this.monthOfBirth = monthOfBirth;
    return this;
  }

  public ContactData withYearOfBirth(String yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
    return this;
  }

    public int getId() { return id; }

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

    public String getHomePhone() { return homePhone; }

    public String getMobilePhone() {
      return mobilePhone;
    }

    public String getWorkPhone() { return workPhone; }

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


  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    return firstName != null ? firstName.equals(that.firstName) : that.firstName == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    return result;
  }

}
