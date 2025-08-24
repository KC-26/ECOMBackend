package com.local.ecom.data.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_users")
public class AdminUser extends BaseUser {

    // No extra fields, BaseUser already has @JsonProperty on its fields.
}