package ua.sampsa.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")

public class ContactData {
  @XStreamOmitField
  private int id = 0;
  @Expose
  private String lastName;
  private String middleName;
  @Expose
  private String firstName;
  @Expose
  private String nickName;
  @Expose
  private String companyName;
  @Expose
  private String address;
  @Expose
  private String homePhone;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  private String allEMails;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @Expose
  private String dayOfBirth;
  @Expose
  private String monthOfBirth;
  @Expose
  private String yearOfBirth;
  private String allPhones;
  private File photo;

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withAllEMails(String allEMails) {
    this.allEMails = allEMails;
    return this;
  }

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

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
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

    public String getAddress() { return address; }

    public String getAllPhones() { return allPhones; }

    public String getHomePhone() { return homePhone; }

    public String getMobilePhone() {
      return mobilePhone;
    }

    public String getWorkPhone() { return workPhone; }

    public String getAllEMails() { return allEMails; }

    public String getEmail() {
    return email;
  }

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getDayOfBirth() {
      return dayOfBirth;
    }

    public String getMonthOfBirth() {
      return monthOfBirth;
    }

    public String getYearOfBirth() {
      return yearOfBirth;
    }

    public File getPhoto() { return photo; }


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
