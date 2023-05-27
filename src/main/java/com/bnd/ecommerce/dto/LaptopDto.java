package com.bnd.ecommerce.dto;

public class LaptopDto extends ProductDto {

  private String screen;

  private String cpu;

  private String ram;

  private String hardDrive;

  private String graphicsCard;

  private String operatingSystem;

  private int releaseYear;

  private String connector;
  private String material;

  private String sizeWeight;

  public String getScreen() {
    return screen;
  }

  public void setScreen(String screen) {
    this.screen = screen;
  }

  public String getCpu() {
    return cpu;
  }

  public void setCpu(String cpu) {
    this.cpu = cpu;
  }

  public String getRam() {
    return ram;
  }

  public void setRam(String ram) {
    this.ram = ram;
  }

  public String getHardDrive() {
    return hardDrive;
  }

  public void setHardDrive(String hardDrive) {
    this.hardDrive = hardDrive;
  }

  public String getGraphicsCard() {
    return graphicsCard;
  }

  public void setGraphicsCard(String graphicsCard) {
    this.graphicsCard = graphicsCard;
  }

  public String getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public int getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
  }

  public String getConnector() {
    return connector;
  }

  public void setConnector(String connector) {
    this.connector = connector;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public String getSizeWeight() {
    return sizeWeight;
  }

  public void setSizeWeight(String sizeWeight) {
    this.sizeWeight = sizeWeight;
  }
}
