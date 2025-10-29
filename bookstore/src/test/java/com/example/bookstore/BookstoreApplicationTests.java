package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bookstore.web.AuthController;
import com.example.bookstore.web.BookController;
import com.example.bookstore.web.BookRestController;
import com.example.bookstore.web.CategoryController;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc	// MockMvc simuloi HTTP-pyyntöjä ilman, että oikeasti käynnistetään palvelinta (Tomcat)
@SpringBootTest 		// annotaatio kertoo, että koko sovellus käynnistetään testin ajaksi


class BookstoreApplicationTests {

	//injektoidaan testattavat kontrollerit
	@Autowired
	private BookController bookController;
	@Autowired
	private BookRestController bookRestController;
	@Autowired
	private CategoryController categoryController;
	@Autowired
	private AuthController authController;

	@Autowired
    private MockMvc mockMvc;	//tuodaan MockMvc avuksi


	// SMOKE TESTI
	@Test // annotaatio kertoo, että kyseessä on testimetodi
	public void contextLoads() {
		assertThat(bookController).isNotNull();
		assertThat(bookRestController).isNotNull();
		assertThat(categoryController).isNotNull();
		assertThat(authController).isNotNull();
	}


// BookControllerin metodien testit ´(näkymät latautuu oikein). en saanut kaikkia toimimaan.

// booklist
// 	@Test
// public void testBooklistPageLoads() throws Exception {
//     mockMvc.perform(get("/login")
//         .param("username", "testuser")
//         .param("password", "testpassword")
		
//         .andExpect(status().isOk())
//     )
//     .perform(get("/booklist"))
//     .andExpect(status().isOk())
//     .andExpect(view().name("booklist"));
// }

// // addbook
//     @Test
//     public void testAddBookPageLoads() throws Exception {
//         mockMvc.perform(get("/addbook"))
//             .andExpect(status().isOk())
//             .andExpect(view().name("addbook"));
//     }

// // save (en saanut toimimaan, ei tunnista post-metodia)

// // @Test
// //     public void testSaveBook() throws Exception {
// //          mockMvc.perform(post("/save"))
// //             .andExpect(status().is3xxRedirection());
// //     }

// //editbook
// 	@Test
//     public void testEditBook() throws Exception {
//          mockMvc.perform(get("/editbook/1"))
//             .andExpect(status().is3xxRedirection());
//     }

// // deletebook

// // kun ei admin 
// 	@Test
// 	public void testDeleteBookRedirectsWhenNotAdmin() throws Exception {
// 		mockMvc.perform(get("/deletebook/1"))
// 			.andExpect(status().is3xxRedirection());
// 	} 

// // kun admin  (EN SAANUT TOIMIMAAN, ei tunnista user-metodia )
// // @Test 
// // public void testDeleteBookWhenAdmin() throws Exception {
// // 	mockMvc.perform(get("/deletebook/1")
// // 			.with(user("admin").password("password").roles("ADMIN")))
// // 		.andExpect(status().is3xxRedirection());
// // }


}
