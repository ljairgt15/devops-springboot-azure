provider "azurerm" {
  features {}
}
#PROVEEDOR AZURE RESOURCE MANAGER

# Crear Grupo de recursos
resource "azurerm_resource_group" "rg" {
  name     = "devops"
  location = "eastus2"
}

# Crear Registro de contenedores
resource "azurerm_container_registry" "acr" {
  name                = "registrai"
  resource_group_name = azurerm_resource_group.rg.name
  location            = azurerm_resource_group.rg.location
  sku                 = "Basic"
  admin_enabled       = true
}

# Clúster de AKS
resource "azurerm_kubernetes_cluster" "aks" {
  name                = "DevOpsAKS"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix          = "devops-aks"

  default_node_pool {
    name       = "default"
    node_count = 2
    vm_size    = "Standard_B2s"
  }

  identity {
    type = "SystemAssigned"
  }

  tags = {
    environment = "DevOpsChallenge"
  }
}
