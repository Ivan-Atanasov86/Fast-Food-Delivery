package com.myNew.FastFoodDelivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foods")
@Builder
@Getter
public class Food {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Long id;

        @NotNull
        @Column(nullable = false,unique = true)
        private String foodName;
}
