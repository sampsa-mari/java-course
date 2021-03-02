package ua.sampsa.addressbook.model;

/**
 * Created by Maryna Tkachuk on 15.12.20.
 */
public class ContactData {
    private int id;
    private final String lastName;
    private final String middleName;
    private final String firstName;
    private final String nickName;
    private final String companyName;
    private final String address;
    private final String mobilePhone;
    private final String email;
    private final String dayOfBirth;
    private final String monthOfBirth;
    private final String yearOfBirth;

    public ContactData(int id, String lastName, String firstName, String middleName,  String nickName, String companyName, String address, String mobilePhone, String email, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
      this.id = id;
      this.lastName = lastName;
      this.firstName = firstName;
      this.middleName = middleName;
      this.nickName = nickName;
      this.companyName = companyName;
      this.address = address;
      this.mobilePhone = mobilePhone;
      this.email = email;
      this.dayOfBirth = dayOfBirth;
      this.monthOfBirth = monthOfBirth;
      this.yearOfBirth = yearOfBirth;
    }

  public ContactData(String lastName, String firstName, String middleName, String nickName, String companyName, String address, String mobilePhone, String email, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
    this.id = 0;
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.nickName = nickName;
    this.companyName = companyName;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.dayOfBirth = dayOfBirth;
    this.monthOfBirth = monthOfBirth;
    this.yearOfBirth = yearOfBirth;
  }

  public void setId(int id) {
    this.id = id;
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
