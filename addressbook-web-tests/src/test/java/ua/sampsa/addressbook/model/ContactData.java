package ua.sampsa.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = 0;

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", nickName='" + nickName + '\'' +
            ", companyName='" + companyName + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", dayOfBirth='" + dayOfBirth + '\'' +
            ", monthOfBirth='" + monthOfBirth + '\'' +
            ", yearOfBirth='" + yearOfBirth + '\'' +
            '}';
  }

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Column(name = "middlename")
  private String middleName;

  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = "nickname")
  private String nickName;

  @Transient
  private String group;

  @Expose
  @Column(name = "company")
  private String companyName;

  @Expose
  @Type(type = "text")
  @Column(name = "address")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allEMails;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Expose
  @Column(name = "bday", columnDefinition = "TINYINT")
  private String dayOfBirth;

  @Expose
  @Column(name = "bmonth")
  private String monthOfBirth;

  @Expose
  @Column(name = "byear")
  private String yearOfBirth;

  @Transient
  private String allPhones;

  @Column(name = "photo",columnDefinition = "mediumtext")
  private String photo;

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
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

  public ContactData group(String nickName) {
    this.nickName = group;
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

    public String group() {
    return group;
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

    public File getPhoto() {
      if (photo == null) {
        return null;
      } else {
        return new File(photo);
      } }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(companyName, that.companyName) && Objects.equals(address, that.address) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastName, firstName, companyName, address, homePhone, mobilePhone, workPhone, email, email2, email3);
  }
}
