-- --------------------------------------------------------
--
-- Structure de la table `bidlist`
--

CREATE TABLE IF NOT EXISTS bidlist (
  bid_list_id INT NOT NULL AUTO_INCREMENT,
  account VARCHAR(255) NOT NULL,
  ask DOUBLE ,
  ask_quantity DOUBLE ,
  benchmark VARCHAR(255) ,
  bid DOUBLE ,
  bid_list_date TIMESTAMP ,
  bid_quantiy DOUBLE NOT NULL,
  book VARCHAR(255) ,
  commentary VARCHAR(255) ,
  creation_date TIMESTAMP ,
  creation_name VARCHAR(255) ,
  deal_name VARCHAR(255) ,
  deal_type VARCHAR(255) ,
  revision_date TIMESTAMP ,
  revision_name VARCHAR(255) ,
  security VARCHAR(255) ,
  side VARCHAR(255) ,
  source_list_id VARCHAR(255) ,
  status VARCHAR(255) ,
  trader VARCHAR(255) ,
  type VARCHAR(255) NOT NULL,
  PRIMARY KEY (bid_list_id)
);

--
-- Structure de la table `curvepoint`
--

CREATE TABLE IF NOT EXISTS curvepoint (
  id INT NOT NULL AUTO_INCREMENT,
  as_of_date TIMESTAMP ,
  creation_date TIMESTAMP ,
  curve_id INT ,
  term DOUBLE NOT NULL,
  value_col DOUBLE NOT NULL,
  PRIMARY KEY (id)
);

--
-- Structure de la table `rating`
--

CREATE TABLE IF NOT EXISTS rating (
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

CREATE TABLE IF NOT EXISTS rulename (
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

CREATE TABLE IF NOT EXISTS trade (
  trade_id INT NOT NULL AUTO_INCREMENT,
  account VARCHAR(255) NOT NULL,
  benchmark VARCHAR(255) ,
  book VARCHAR(255) ,
  buy_price DOUBLE ,
  buy_quantity DOUBLE NOT NULL,
  creation_date TIMESTAMP ,
  creation_name VARCHAR(255) ,
  deal_name VARCHAR(255) ,
  deal_type VARCHAR(255) ,
  revision_date TIMESTAMP ,
  revision_name VARCHAR(255) ,
  security VARCHAR(255) ,
  sell_price DOUBLE ,
  sell_quantity DOUBLE ,
  side VARCHAR(255) ,
  source_list_id VARCHAR(255) ,
  status VARCHAR(255) ,
  trade_date TIMESTAMP ,
  trader VARCHAR(255) ,
  type VARCHAR(255) NOT NULL,
  PRIMARY KEY (trade_id)
);

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  fullname VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
