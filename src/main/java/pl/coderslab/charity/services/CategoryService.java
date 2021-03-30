package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService implements SimpleServices<Category> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> all() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findOne(int id) {
        return categoryRepository.findTopById(id);
    }

    public List<Category> name() {
        return categoryRepository.findAllByName();
    }
}
