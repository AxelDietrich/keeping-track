package com.keepingtrack.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "balances")
public class Balance {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "available_amount", nullable = false)
    private double availableAmount;

    @Column(name = "savings_amount", nullable = false)
    private double savingsAmount;

    @Column(nullable = false)
    private double debt;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Balance(Long id, double availableAmount, double savingsAmount, double debt, Account account) {
        this.id = id;
        this.availableAmount = availableAmount;
        this.savingsAmount = savingsAmount;
        this.debt = debt;
        this.account = account;
    }

    public Balance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public double getSavingsAmount() {
        return savingsAmount;
    }

    public void setSavingsAmount(double savingsAmount) {
        this.savingsAmount = savingsAmount;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
