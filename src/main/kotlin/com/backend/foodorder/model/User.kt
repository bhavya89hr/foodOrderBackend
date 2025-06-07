package com.backend.foodorder.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @Column(unique = true)
    val email: String,

    val _password: String,

    @Enumerated(EnumType.STRING)
    val role: Role
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf()
        // optionally: listOf(SimpleGrantedAuthority("ROLE_${role.name}"))
    }

    override fun getPassword() = _password
    override fun getUsername() = email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
