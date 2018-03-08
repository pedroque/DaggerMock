package com.example.pedroabinajm.data.repository.datasource

import com.example.pedroabinajm.domain.Hamburger
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class CloudHamburguersDataSource : HamburguerDataSource{
    override fun getHamburguers(): Observable<List<Hamburger>> {
        return Observable.just(listOf(
                Hamburger("Holy Burguer", 5f, "R. Dr. Cesário Mota Júnior, 527"),
                Hamburger("General Prime Burguer", 4f, "R. Joaquim Floriano, 541"),
                Hamburger("Butcher's Market", 4.5f, "R. Bandeira Paulista, 164"),
                Hamburger("Luz, Câmera, Burger!", 4f, "Rua Caravelas, 339"),
                Hamburger("Burger Joint", 4f, "Shopping Top Center - Av. Paulista, 854, 2º andar "),
                Hamburger("St. Louis", 4.5f, "R. Batataes, 242"),
                Hamburger("Z Deli Sandwich Shop", 4f, "Rua Haddock Lobo, 1386"),
                Hamburger("Big Kahuna", 4.5f, "Alameda Lorena, 53"),
                Hamburger("Mr. Mills", 3.5f, "R. Abílio Soares, 165"),
                Hamburger("A Chapa", 2.5f, "Alameda Santos, 24"),
                Hamburger("Fifties", 2f, "Shopping Eldorado"),
                Hamburger("Osnir", 3f, "Av. Jabaquara, 538")
        )).delay(3, TimeUnit.SECONDS)
    }

}