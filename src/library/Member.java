package library;

import java.util.*;

public class Member {
    private static int currentNum = 100;
    private int memberNum;
    private int id;
    private String name;
    private ArrayList<Loan> loans;

    public Member(String name, int id) {
        this.memberNum = currentNum++;
        this.id = id;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    // Getters & Setters
    public int getMemberNum() {
        return this.memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return this.loans;
    }

    // Overload of Object's equals method with Member parameter
    public boolean equals(Member member) {
        if (this == member) return true;

        return this.memberNum == member.memberNum &&
                this.id == member.id &&
                this.name.equals(member.name);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNum=" + this.memberNum +
                ", id=" + this.id +
                ", name=" + this.name +
                ", loans=" + this.loans +
                '}';
    }
}
