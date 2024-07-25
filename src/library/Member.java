package library;

import java.util.*;

public class Member {
    // Static member number
    private static int currentNum = 100;
    // Member number
    private int memberNum;
    // ID
    private int id;
    // Name
    private String name;
    // Loans list
    private ArrayList<Loan> loans;

    // Constructor
    public Member(int id, String name) {
        this.memberNum = currentNum++;
        this.id = id;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    // Getters & Setters
    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    // Overload of Object's equals method with Member parameter
    public boolean equals(Member member) {
        if (this == member) return true;

        return this.id == member.id &&
                this.name.equals(member.name);
    }

    // toString method
    @Override
    public String toString() {
        return "Member{" +
                "memberNum=" + memberNum +
                ", id=" + id +
                ", name=" + name +
                ", loans=" + loans +
                '}';
    }

}
