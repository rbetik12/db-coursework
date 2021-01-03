package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.FactoryInputItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactoryInputItemRepository extends JpaRepository<FactoryInputItem, Long> {

    @Query(value = "with recursive items as (\n" +
            "    select id, item_id, next_item\n" +
            "    from factory_input_item\n" +
            "    where id = ?1\n" +
            "    union all\n" +
            "        select input.id, input.item_id, input.next_item\n" +
            "        from factory_input_item input\n" +
            "            join items on input.id = items.next_item\n" +
            ")\n" +
            "select * from items;", nativeQuery = true)
    List<FactoryInputItem> getItems(long id);
}
