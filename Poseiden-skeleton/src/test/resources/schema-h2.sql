-- --------------------------------------------------------
--
-- Structure de la table `bidlist`
--

CREATE TABLE bidlist (
  bid_list_id INT NOT NULL AUTO_INCREMENT,
  account VARCHAR(255) NOT NULL,
  ask DOUBLE DEFAULT NULL,
  ask_quantity DOUBLE DEFAULT NULL,
  benchmark VARCHAR(255) DEFAULT NULL,
  bid DOUBLE DEFAULT NULL,
  bid_list_date TIMESTAMP DEFAULT NULL,
  bid_quantiy DOUBLE NOT NULL,
  book VARCHAR(255) DEFAULT NULL,
  commentary VARCHAR(255) DEFAULT NULL,
  creation_date TIMESTAMP DEFAULT NULL,
  creation_name VARCHAR(255) DEFAULT NULL,
  deal_name VARCHAR(255) DEFAULT NULL,
  deal_type VARCHAR(255) DEFAULT NULL,
  revision_date TIMESTAMP DEFAULT NULL,
  revision_name VARCHAR(255) DEFAULT NULL,
  security VARCHAR(255) DEFAULT NULL,
  side VARCHAR(255) DEFAULT NULL,
  source_list_id VARCHAR(255) DEFAULT NULL,
  status VARCHAR(255) DEFAULT NULL,
  trader VARCHAR(255) DEFAULT NULL,
  type VARCHAR(255) NOT NULL,
  PRIMARY KEY (bid_list_id)
);

--
-- Structure de la table `curvepoint`
--

CREATE TABLE curvepoint (
  id INT NOT NULL AUTO_INCREMENT,
  as_of_date TIMESTAMP DEFAULT NULL,
  creation_date TIMESTAMP DEFAULT NULL,
  curve_id INT DEFAULT NULL,
  term DOUBLE NOT NULL,
  value DOUBLE NOT NULL,
  PRIMARY KEY (id)
);

--
-- Structure de la table `rating`
--

CREATE TABLE rating (
  id INT NOT NULL AUTO_INCREMENT,
  fitch_rating VARCHAR(255) NOT NULL,
  moodys_rating VARCHAR(255) NOT NULL,
  order_number INT NOT NULL,
  sandprating VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Structure de la table `rulename`
--

CREATE TABLE rulename (
  id INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(255) NOT NULL,
  json VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  sql_part VARCHAR(255) NOT NULL,
  sql_str VARCHAR(255) NOT NULL,
  template VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Structure de la table `trade`
--

CREATE TABLE trade (
  trade_id INT NOT NULL AUTO_INCREMENT,
  account VARCHAR(255) NOT NULL,
  benchmark VARCHAR(255) DEFAULT NULL,
  book VARCHAR(255) DEFAULT NULL,
  buy_price DOUBLE DEFAULT NULL,
  buy_quantity DOUBLE NOT NULL,
  creation_date TIMESTAMP DEFAULT NULL,
  creation_name VARCHAR(255) DEFAULT NULL,
  deal_name VARCHAR(255) DEFAULT NULL,
  deal_type VARCHAR(255) DEFAULT NULL,
  revision_date TIMESTAMP DEFAULT NULL,
  revision_name VARCHAR(255) DEFAULT NULL,
  security VARCHAR(255) DEFAULT NULL,
  sell_price DOUBLE DEFAULT NULL,
  sell_quantity DOUBLE DEFAULT NULL,
  side VARCHAR(255) DEFAULT NULL,
  source_list_id VARCHAR(255) DEFAULT NULL,
  status VARCHAR(255) DEFAULT NULL,
  trade_date TIMESTAMP DEFAULT NULL,
  trader VARCHAR(255) DEFAULT NULL,
  type VARCHAR(255) NOT NULL,
  PRIMARY KEY (trade_id)
);

--
-- Structure de la table `users`
--

CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  fullname VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
