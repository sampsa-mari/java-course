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
    private String allPhones;



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

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
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

    public String getAllPhones() { return allPhones; }


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
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
    if (monthOfBirth != null ? !monthOfBirth.equals(that.monthOfBirth) : that.monthOfBirth != null) return false;
    if (yearOfBirth != null ? !yearOfBirth.equals(that.yearOfBirth) : that.yearOfBirth != null) return false;
    return allPhones != null ? allPhones.equals(that.allPhones) : that.allPhones == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (monthOfBirth != null ? monthOfBirth.hashCode() : 0);
    result = 31 * result + (yearOfBirth != null ? yearOfBirth.hashCode() : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
    return result;
  }
}
