SELECT setval('listings_listing_id_seq', (SELECT MAX(listing_id) FROM listing));